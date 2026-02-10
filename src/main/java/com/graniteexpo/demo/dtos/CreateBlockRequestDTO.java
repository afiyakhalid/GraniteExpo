package com.graniteexpo.demo.dtos;
import jakarta.validation.constraints.NotBlank;
public class CreateBlockRequestDTO {
    @NotBlank
    private String blockCode;

    public String getBlockCode() { return blockCode; }
    public void setBlockCode(String blockCode) { this.blockCode = blockCode; }
}
