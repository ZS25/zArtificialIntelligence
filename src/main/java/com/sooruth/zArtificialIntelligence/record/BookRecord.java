package com.sooruth.zArtificialIntelligence.record;

public record BookRecord(
        String title,
        String author,
        int publicationYear,
        String genre,
        int pageCount,
        String summary) {
}
