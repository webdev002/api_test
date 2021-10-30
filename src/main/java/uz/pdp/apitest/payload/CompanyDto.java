package uz.pdp.apitest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "corpName bosh bolishi mimkin emas")
    private String corpName;

    @NotNull(message = "directorName bosh bolishi mumkin emas")
    private String directorName;

    @NotNull(message = "Address bosh bolishi mumkin emas")
    private Integer AddressId;
}
