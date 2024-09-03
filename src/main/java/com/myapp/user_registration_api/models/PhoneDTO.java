package com.myapp.user_registration_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {
    private String Number;
    private String CityCode;
    private String CountryCcde;
}
