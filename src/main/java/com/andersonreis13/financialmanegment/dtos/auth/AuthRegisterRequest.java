package com.andersonreis13.financialmanegment.dtos.auth;

public record AuthRegisterRequest(Long id,
                                  String email,
                                  String password,
                                  String name){

}
