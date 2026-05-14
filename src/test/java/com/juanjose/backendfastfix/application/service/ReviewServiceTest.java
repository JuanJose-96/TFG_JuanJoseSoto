package com.juanjose.backendfastfix.application.service;

import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.application.port.out.ReviewRepositoryPort;
import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.domain.exception.ReviewAlreadyExistsException;
import com.juanjose.backendfastfix.domain.exception.UnauthorizedReviewAccessException;
import com.juanjose.backendfastfix.domain.model.Client;
import com.juanjose.backendfastfix.domain.model.Review;
import com.juanjose.backendfastfix.domain.model.Sector;
import com.juanjose.backendfastfix.domain.model.Technician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private ReviewRepositoryPort reviewRepositoryPort;

    @Mock
    private ClientRepositoryPort clientRepositoryPort;

    @Mock
    private TechnicianRepositoryPort technicianRepositoryPort;

    @InjectMocks
    private ReviewService reviewService;

    private Client testClient;
    private Technician testTechnician;

    @BeforeEach
    void setup(){
        testClient = Client.builder()
                .id(1L)
                .name("Juan")
                .surname("García")
                .email("juan@gmail.com")
                .password("oculta")
                .phone("611234456")
                .province("Murcia").city("Cartagena").build();

        testTechnician = Technician.builder()
                .id(1L)
                .name("Pedro")
                .surname("López")
                .email("pedro@gmail.com")
                .password("oculta")
                .phone("622123123")
                .province("Murcia")
                .city("Cartagena").sector(new Sector(1L,"Electricidad"))
                .averageRating(0.0)
                .totalReviews(0)
                .build();

    }

    @Test
    @DisplayName("Publis review should save review and update technician average rating")
    void publishReviewSavesAndUpdatesRating(){
        when(clientRepositoryPort.findById(1L)).thenReturn(Optional.of(testClient));
        when(technicianRepositoryPort.findById(1L)).thenReturn(Optional.of(testTechnician));
        when(reviewRepositoryPort.existsByClientAndTechnicianId(1L,1L)).thenReturn(false);

        Review savedReview = Review.builder()
                .id(1L)
                .clientId(1L)
                .technicianId(1L)
                .rating(5)
                .comment("Excelente trabajo")
                .createdAt(LocalDateTime.now()).build();

        when(reviewRepositoryPort.save(any(Review.class))).thenReturn(savedReview);
        when(reviewRepositoryPort.findByTechnicianId(1L)).thenReturn(List.of(savedReview));

        Review result = reviewService.publish(1L,1L,5,"Excelente trabajo");

        assertNotNull(result);
        assertEquals(5,result.getRating());
        assertEquals("Excelente trabajo",result.getComment());

        ArgumentCaptor<Technician> techCaptor = ArgumentCaptor.forClass(Technician.class);
        verify(technicianRepositoryPort).save(techCaptor.capture());

        Technician updatedTechnician = techCaptor.getValue();
        assertEquals(5.0,updatedTechnician.getAverageRating());
        assertEquals(1,updatedTechnician.getTotalReviews());

    }

    @Test
    @DisplayName("Publish review should throw exception when client already reviewed technician")
    void publishReviewThrowsWhenDuplicate(){
        when(clientRepositoryPort.findById(1L)).thenReturn(Optional.of(testClient));
        when(technicianRepositoryPort.findById(1L)).thenReturn(Optional.of(testTechnician));
        when(reviewRepositoryPort.existsByClientAndTechnicianId(1L,1L)).thenReturn(true);

        assertThrows(ReviewAlreadyExistsException.class, ()-> reviewService.publish(1L,1L,5,"Segundo intento"));

        verify(reviewRepositoryPort,never()).save(any());
    }

    @Test
    @DisplayName("Edit review should throw exception when client is not the owner")
    void editReviewThrowsWhenNotOwner(){
        Review existingReview = Review.builder()
                .id(1L)
                .clientId(1L)
                .technicianId(1L)
                .rating(5)
                .comment("Comentario original")
                .createdAt(LocalDateTime.now()).build();

        when(reviewRepositoryPort.findById(1L)).thenReturn(Optional.of(existingReview));

        assertThrows(UnauthorizedReviewAccessException.class, ()->
                reviewService.edit(1L,999L,3,"Intento de editar"));

        verify(reviewRepositoryPort,never()).save(any());

    }


}
