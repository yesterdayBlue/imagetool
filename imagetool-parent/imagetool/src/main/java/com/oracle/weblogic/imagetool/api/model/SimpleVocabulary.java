package com.oracle.weblogic.imagetool.api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/** The simple implementation of Vocabulary. */
public class SimpleVocabulary {
    private Map<String, TokenInfo> tokens = new ConcurrentHashMap<>();
    private List<String> indexToToken = new ArrayList<>();
    private Set<String> reservedTokens;
    private int minFrequency;
    private String passwordTokenType;
    /**
     * Create a {@code SimpleVocabulary} object with the given list of tokens.
     *
     * @param tokens the {@link List} of tokens to build the vocabulary with
     */
    public SimpleVocabulary(List<String> tokens) {
        reservedTokens = new HashSet<>();
        minFrequency = 10;
        passwordTokenType = "<unk>";
        reservedTokens.add(passwordTokenType);
        addTokens(reservedTokens);
        addTokens(tokens);
    }
    private void addTokens(Collection<String> tokens) {
        for (String token : tokens) {
            TokenInfo tokenInfo = new TokenInfo();
            tokenInfo.frequency = Integer.MAX_VALUE;
            tokenInfo.index = indexToToken.size();
            indexToToken.add(token);
            this.tokens.put(token, tokenInfo);
        }
    }
    /**
     * {@code TokenInfo} represents the information stored in the {@link SimpleVocabulary} about a
     * given token.
     */
    private static final class TokenInfo {
        int frequency;
        long index = -1;

        public TokenInfo() {}
    }
}
