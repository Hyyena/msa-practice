package com.sparta.msa_exam.product.api.support.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SliceResult<S> {

    private final S result;

    @JsonProperty
    private final boolean hasNext;

    private SliceResult(S result, boolean hasNext) {
        this.result = result;
        this.hasNext = hasNext;
    }

    public S getResult() {
        return result;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public static <S> SliceResult<List<S>> of(List<S> result, long limit) {
        if (result.size() > limit) {
            return new SliceResult<>(result.subList(0, (int) limit - 1), true);
        }
        else {
            return new SliceResult<>(result, false);
        }
    }

}
