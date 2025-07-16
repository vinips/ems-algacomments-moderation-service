package com.algawors.algacomments.moderation.service.api.controller;

import com.algawors.algacomments.moderation.service.api.model.ModerationInput;
import com.algawors.algacomments.moderation.service.api.model.ModerationOutput;
import com.algawors.algacomments.moderation.service.domain.ForbiddenWords;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/moderate")
public class ModerationController {

    private static final String FORBIDDEN_WORD_FOUND = "Comentário negado: Uma palavra proíbida foi encontrada. Favor revisar.";

    @PostMapping
    public ModerationOutput approve(@RequestBody ModerationInput moderationInput) {

        String[] forbiddenWords = {ForbiddenWords.ODIO.getValue(), ForbiddenWords.XINGAMENTO.getValue()};

        boolean forbiddenWordFound = Stream.of(forbiddenWords)
                .anyMatch(word -> moderationInput.getText().toLowerCase().contains(word));

        String reason = forbiddenWordFound ? FORBIDDEN_WORD_FOUND : null;

        return ModerationOutput.builder()
                .approved(!forbiddenWordFound)
                .reason(reason)
                .build();
    }

}
