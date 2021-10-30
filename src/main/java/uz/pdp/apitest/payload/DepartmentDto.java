package uz.pdp.apitest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.apitest.entity.Company;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    @NotNull(message = "name bosh bolishi mumkin emas")
    private String name;

    @NotNull(message = "company bosh bolishi mumkin emas")
    private Integer companyId;

}
