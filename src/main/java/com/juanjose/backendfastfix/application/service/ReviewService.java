package com.juanjose.backendfastfix.application.service;

import com.juanjose.backendfastfix.application.port.in.review.*;
import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.application.port.out.ReviewRepositoryPort;
import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.domain.exception.*;
import com.juanjose.backendfastfix.domain.model.Review;
import com.juanjose.backendfastfix.domain.model.Technician;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService implements PublishReviewUseCase,
        GetClientReviewsUseCase, GetTechnicianReviewsUseCase,
        ReplyReviewUseCase, EditReviewUseCase {

    private final ClientRepositoryPort clientRepositoryPort;
    private final ReviewRepositoryPort reviewRepositoryPort;
    private final TechnicianRepositoryPort technicianRepositoryPort;

    public ReviewService(ClientRepositoryPort clientRepositoryPort, ReviewRepositoryPort reviewRepositoryPort, TechnicianRepositoryPort technicianRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
        this.reviewRepositoryPort = reviewRepositoryPort;
        this.technicianRepositoryPort = technicianRepositoryPort;
    }

    @Override
    public List<Review> getReviewsByClientId(Long clientId) {
        clientRepositoryPort.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        return reviewRepositoryPort.findByClientId(clientId);

    }

    @Override
    public List<Review> getReviewsByTechnicianId(Long technicianId) {
        technicianRepositoryPort.findById(technicianId)
                .orElseThrow(() -> new TechnicianNotFoundException(technicianId));

        return reviewRepositoryPort.findByTechnicianId(technicianId);

    }

    @Override
    public Review publish(Long clientId, Long technicianId, int rating, String comment) {
        if(rating < 1 || rating > 5){
            throw new InvalidRatingException();
        }
        clientRepositoryPort.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        Technician technician = technicianRepositoryPort.findById(technicianId)
                .orElseThrow(() -> new TechnicianNotFoundException(technicianId));

        if(reviewRepositoryPort.existsByClientAndTechnicianId(clientId,technicianId)){
            throw new ReviewAlreadyExistsException();
        }
        Review review = Review.builder()
                .clientId(clientId)
                .technicianId(technicianId)
                .rating(rating)
                .comment(comment)
                .createdAt(LocalDateTime.now()).build();

       Review savedReview = reviewRepositoryPort.save(review);
       updateTechnicianRating(technician);
       return savedReview;



    }

    private void updateTechnicianRating(Technician technician){
        List<Review> reviewsTechnician = reviewRepositoryPort.findByTechnicianId(technician.getId());

        double rating = reviewsTechnician.stream()
                .mapToInt(review -> review.getRating())
                .average().orElse(0.0);

        Technician updateTechnician = technician.toBuilder()
                .averageRating(rating)
                .totalReviews(reviewsTechnician.size())
                .build();

        technicianRepositoryPort.save(updateTechnician);

    }

    @Override
    public Review reply(Long reviewId, Long technicianId, String technicianReply) {
        Review review = reviewRepositoryPort.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));

        if(!review.getTechnicianId().equals(technicianId)){
            throw new TechnicianNotFoundException(technicianId);
        }
        Review updatedReview = review.toBuilder()
                .technicianReply(technicianReply)
                .technicianReplyDate(LocalDateTime.now())
                .build();

        return reviewRepositoryPort.save(updatedReview);
    }

    @Override
    public Review edit(Long reviewId, Long clientId, Integer newRating, String newComment) {
        Review review = reviewRepositoryPort.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException(reviewId));
        if(!review.getClientId().equals(clientId)){
            throw new UnauthorizedReviewAccessException();
        }

        Review updatedReview = review.toBuilder()
                .rating(newRating != null ? newRating : review.getRating())
                .comment(newComment != null ? newComment : review.getComment())
                .build();

        Review savedReview = reviewRepositoryPort.save(updatedReview);
        Technician technician = technicianRepositoryPort.findById(review.getTechnicianId())
                .orElseThrow(() -> new TechnicianNotFoundException(review.getTechnicianId()));

        updateTechnicianRating(technician);
        return savedReview;
    }
}
