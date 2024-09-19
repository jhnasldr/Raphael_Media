package com.example.customerservic.services;
import com.example.customerservic.entities.MediaInteractions;
import com.example.customerservic.repositories.MediaInteractionsRepository;
import com.mysql.cj.xdevapi.Warning;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class MediaInteractionsServiceTest {

    private MediaInteractionsRepository mockMediaInteractionsRepository;
    private MediaInteractionsService mockMediaInteractionsService;
    private org.apache.log4j.Logger mockLogger;
    private MediaInteractions mockMediaInteraction;


    @BeforeEach
    void setUp() {
        mockMediaInteractionsRepository = mock(MediaInteractionsRepository.class);
        mockLogger = mock(Logger.class);
        mockMediaInteractionsService = new MediaInteractionsService();
        mockMediaInteractionsService.setMediaInteractionsRepository(mockMediaInteractionsRepository);
        mockMediaInteractionsService.setLogger(mockLogger);

        mockMediaInteraction = mock(MediaInteractions.class);

    }

    @Test
    void addMediaInteraction_shouldSaveMediaInteraction() {
        mockMediaInteractionsService.addMediaInteraction(mockMediaInteraction);

        verify(mockMediaInteractionsRepository).save(mockMediaInteraction);

    }

    @Test
    void addMediaInteraction_ShouldLogWarningMessage() {
        mockMediaInteractionsService.addMediaInteraction(mockMediaInteraction);

        verify(mockLogger).log(eq(Priority.WARN), eq("New media interaction created"));
    }

    @Test
    void addMediaInteraction_shouldReturnSameMediaInteraction() {
        when(mockMediaInteractionsRepository.save(mockMediaInteraction)).thenReturn(mockMediaInteraction);

        MediaInteractions result = mockMediaInteractionsService.addMediaInteraction(mockMediaInteraction);

        assertEquals(mockMediaInteraction, result);
    }


}