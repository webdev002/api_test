package uz.pdp.apitest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.apitest.entity.Address;
import uz.pdp.apitest.entity.Department;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    @NotNull(message = "name bosh bolishi mumkin emas")
    private String name;

    @NotNull(message = "phoneNumber bosh bolishi mukin emas")
    private String phoneNumber;

    @NotNull(message = "address bosh bolishi mumkin emas")
    private Integer addressId;

    @NotNull(message = "department bosh bolishi mumkin emas")
    private Integer departmentId;
}
