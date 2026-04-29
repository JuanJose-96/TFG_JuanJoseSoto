package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller;

import com.juanjose.backendfastfix.application.port.in.review.*;
import com.juanjose.backendfastfix.domain.model.Review;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.review.*;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.ReviewRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final PublishReviewUseCase publishReviewUseCase;
    private final GetClientReviewsUseCase getClientReviewsUseCase;
    private final GetTechnicianReviewsUseCase getTechnicianReviewsUseCase;
    private final ReplyReviewUseCase replyReviewUseCase;
    private final EditReviewUseCase editReviewUseCase;
    private final DeleteReviewUseCase deleteReviewUseCase;
    private final DeleteReplyUseCase deleteReplyUseCase;

    public ReviewController(PublishReviewUseCase publishReviewUseCase, GetClientReviewsUseCase getClientReviewsUseCase, GetTechnicianReviewsUseCase getTechnicianReviewsUseCase, ReplyReviewUseCase replyReviewUseCase, EditReviewUseCase editReviewUseCase, DeleteReviewUseCase deleteReviewUseCase, DeleteReplyUseCase deleteReplyUseCase) {
        this.publishReviewUseCase = publishReviewUseCase;
        this.getClientReviewsUseCase = getClientReviewsUseCase;
        this.getTechnicianReviewsUseCase = getTechnicianReviewsUseCase;
        this.replyReviewUseCase = replyReviewUseCase;
        this.editReviewUseCase = editReviewUseCase;
        this.deleteReviewUseCase = deleteReviewUseCase;
        this.deleteReplyUseCase = deleteReplyUseCase;
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> publish(@Valid @RequestBody PublishReviewRequest request){
        Review savedReview = publishReviewUseCase.publish(
                request.clientId(),
                request.technicianId(),
                request.rating(),
                request.comment()
                );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReviewRestMapper.fromDomain(savedReview));

    }

    @GetMapping("/technician/{technicianId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByTechnician(@PathVariable Long technicianId){
        List<ReviewResponse> reviewResponses =  getTechnicianReviewsUseCase.getReviewsByTechnicianId(technicianId)
                .stream().map(ReviewRestMapper::fromDomain).toList();

        return ResponseEntity.ok(reviewResponses);

    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByClient(@PathVariable Long clientId){
        List<ReviewResponse> reviewResponses = getClientReviewsUseCase
                .getReviewsByClientId(clientId).stream()
                .map(ReviewRestMapper::fromDomain).toList();

        return ResponseEntity.ok(reviewResponses);

    }

    @PatchMapping("/{reviewId}/reply")
    public ResponseEntity<ReviewResponse> reply(@PathVariable Long reviewId,
                                                @Valid @RequestBody ReplyReviewRequest request){
        Review updateReview = replyReviewUseCase.reply(
                reviewId,
                request.technicianId(),
                request.technicianReply()
        );

        return ResponseEntity.ok(ReviewRestMapper.fromDomain(updateReview));
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> edit(@PathVariable Long reviewId,
                                               @Valid @RequestBody EditReviewRequest request){
        Review updatedReview = editReviewUseCase.edit(
                reviewId,
                request.clientId(),
                request.rating(),
                request.comment());

        return ResponseEntity.ok(ReviewRestMapper.fromDomain(updatedReview));

    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> delete(@PathVariable Long reviewId,
                                                 @Valid @RequestBody DeleteReviewRequest request){
        deleteReviewUseCase.delete(reviewId,request.clientId());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reviewId}/reply")
    public ResponseEntity<ReviewResponse> deleteReply(@PathVariable Long reviewId,
                                                      @Valid @RequestBody DeleteReplyRequest request){
        Review reviewReplyDeleted = deleteReplyUseCase.deleteReply(reviewId,request.technicianId());

        return ResponseEntity.ok(ReviewRestMapper.fromDomain(reviewReplyDeleted));

    }
}
