package com.sparta.msa_exam.product.api.support;

import org.springframework.data.domain.Sort;

public record Cursor(String cursor, int limit, String sortKey, Sort.Direction sort) {
}
