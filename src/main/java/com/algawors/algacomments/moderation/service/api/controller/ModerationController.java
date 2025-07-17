package com.algawors.algacomments.moderation.service.api.controller;

import com.algawors.algacomments.moderation.service.api.model.ModerationInput;
import com.algawors.algacomments.moderation.service.api.model.ModerationOutput;
import com.algawors.algacomments.moderation.service.domain.ForbiddenWords;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/moderate")
@Slf4j
public class ModerationController {

    private static final String FORBIDDEN_WORD_FOUND = "Comment Denied: A forbidden word was found. Please, review your comment.";

    @PostMapping
    public ModerationOutput approve(@RequestBody ModerationInput moderationInput) {

        String[] forbiddenWords = {ForbiddenWords.ODIO.getValue(), ForbiddenWords.XINGAMENTO.getValue()};

        boolean forbiddenWordFound = Stream.of(forbiddenWords)
                .anyMatch(word -> moderationInput.getText().toLowerCase().contains(word));

        String reason = forbiddenWordFound ? FORBIDDEN_WORD_FOUND : null;

        ModerationOutput moderationOutput = ModerationOutput.builder()
                .approved(!forbiddenWordFound)
                .reason(reason)
                .build();

        log.info(moderationOutput.toString());

        return moderationOutput;
    }

}
