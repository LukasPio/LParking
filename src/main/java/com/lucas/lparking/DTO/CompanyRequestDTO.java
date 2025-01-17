package com.lucas.lparking.DTO;

public record CompanyRequestDTO(
        String name, String cnpj, String adress, String phone, Integer motorcycleSpots, Integer carSpots
) {
}
