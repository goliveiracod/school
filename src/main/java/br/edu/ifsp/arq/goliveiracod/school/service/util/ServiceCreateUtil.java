package br.edu.ifsp.arq.goliveiracod.school.service.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URI;

@AllArgsConstructor
@Data
public class ServiceCreateUtil<E> {
    private final E Dto;
    private final URI uri;
}
