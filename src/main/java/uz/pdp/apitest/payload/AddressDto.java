package uz.pdp.apitest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotNull(message = "Id bosh bolishi mumkin emas")
    private Integer id;

    @NotNull(message = "street bosh bolishi mumkin emas")
    private String street;

    @NotNull(message = "homeNumber bosh bolishi mumkin emas")
    private Integer homeNumber;
}
