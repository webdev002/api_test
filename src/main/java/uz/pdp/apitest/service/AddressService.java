package uz.pdp.apitest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apitest.entity.Address;
import uz.pdp.apitest.payload.AddressDto;
import uz.pdp.apitest.payload.ApiResponse;
import uz.pdp.apitest.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


    public List<Address> getAddress(){
        List<Address> repositoryAll = addressRepository.findAll();
        return repositoryAll;

    }

    public Address getAddressById(Integer id){
        Optional<Address> address = addressRepository.findById(id);
//       if (address.isPresent()){
//           return address.get();
//       }
//       return null;
        return address.orElse(null);
    }


    public ApiResponse addAddress(AddressDto addressDto){
        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return new ApiResponse("Address saqlandi!",true);
    }


    public ApiResponse editAddress(Integer id,AddressDto addressDto){
        boolean byHomeNumberAndIdNot = addressRepository.existsByHomeNumberAndIdNot(addressDto.getHomeNumber(), id);
        if (byHomeNumberAndIdNot){
            return new ApiResponse("Bunday id lik address mavjud!",false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            return new ApiResponse("Bunday  id lik address mavjud emaas!",false);
        }
        Address address = optionalAddress.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Address tahrirlandi!",true);
    }

    public ApiResponse deleteAddress(Integer id){
        addressRepository.deleteById(id);
        return new ApiResponse("Address ochirildi!",true);
    }
}
