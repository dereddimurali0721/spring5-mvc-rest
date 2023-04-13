package guru.springfamework.config;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    public static  final CustomerMapper CUSTOMER_MAPPER=Mappers.getMapper(CustomerMapper.class);

    CustomerDTO convertCustomertoCustomerDTO(Customer customer);

    Customer convertCustomerDtoToCustomer(CustomerDTO customerDTO);
}
