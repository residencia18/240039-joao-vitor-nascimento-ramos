package com.example.atividades.atividade15;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectTextRequest;
import software.amazon.awssdk.services.rekognition.model.DetectTextResponse;
import software.amazon.awssdk.services.rekognition.model.RekognitionException;
import software.amazon.awssdk.services.rekognition.model.TextDetection;

@TestMethodOrder(MethodOrderer.Random.class)
public class DetectTextImageTest {

    @Test
    public void testDetectTextLabels() {
        RekognitionClient rekClientMock = mock(RekognitionClient.class);

        DetectTextResponse responseMock = mock(DetectTextResponse.class);
        List<TextDetection> textDetections = new ArrayList<>();
        textDetections.add(TextDetection.builder().detectedText("Hello").confidence(0.95f).build());
        when(responseMock.textDetections()).thenReturn(textDetections);

        when(rekClientMock.detectText(any(DetectTextRequest.class))).thenReturn(responseMock);

        DetectTextImage detectTextImage = new DetectTextImage("data/img-example-for-aws-task.jpg", rekClientMock);

        detectTextImage.detectTextLabels("path/to/results.txt");

        verify(rekClientMock).detectText(any(DetectTextRequest.class));
    }

    @Test
    public void testSaveResultToTextFile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<TextDetection> textDetections = new ArrayList<>();
        textDetections.add(TextDetection.builder()
                .detectedText("Hello")
                .confidence(0.95f)
                .id(1)
                .parentId(2)
                .type("LINE")
                .build());

        String fileName = "test_output.txt";

        DetectTextImage detectTextImage = new DetectTextImage(null, null);

        Method saveResultToTextFileMethod = DetectTextImage.class.getDeclaredMethod("saveResultToTextFile", List.class, String.class);
        saveResultToTextFileMethod.setAccessible(true);

        saveResultToTextFileMethod.invoke(detectTextImage, textDetections, fileName);

        File outputFile = new File(fileName);
        assert outputFile.exists() : "O arquivo de saída não foi criado.";

        outputFile.delete();
    }

    @Test
    public void testDetectTextImageConstructor() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        DetectTextImage detectTextImage = new DetectTextImage();

        assertEquals("data/img-example-for-aws-task.jpg", detectTextImage.sourceImage);
        assertEquals(Region.US_WEST_2, detectTextImage.region);

        assertNotNull(detectTextImage.rekClient);

    }






}

