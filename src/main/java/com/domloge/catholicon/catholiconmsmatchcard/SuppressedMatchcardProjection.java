package com.domloge.catholicon.catholiconmsmatchcard;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "suppressedMatchcardProjection", types = { Matchcard.class })
public interface SuppressedMatchcardProjection {

}
