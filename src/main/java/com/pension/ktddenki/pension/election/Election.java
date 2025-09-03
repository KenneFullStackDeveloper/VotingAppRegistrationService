package com.pension.ktddenki.pension.election;

public record Election( Integer id,
                        String title,
                        String startDate,
                        String endDate,
                        String description,
                        Boolean isActivate,
                        Boolean requireAuth
) {
}
