package com.andersonreis13.financialmanegment.dtos.category;

import com.andersonreis13.financialmanegment.dtos.user.UserDTO;

public record CategoryCreateResponse(String msg,
                                     Long id,
                                     UserDTO user,
                                     String name) {
}
