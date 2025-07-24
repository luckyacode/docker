package com.praveen.docker;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Others {
    public static void main(String[] args) throws IOException {
        List<String> list = Arrays.asList("praveen","praveen","tripathi");
        System.out.println(list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        System.out.println("Praveen");
//        ResponseDTO responseDTO = ResponseDTO.builder().message("praveen").
//                status(300).data("tripathi").
//                build();
//        XmlMapper xmlMapper = new XmlMapper();
//        String xml = xmlMapper.writeValueAsString(responseDTO);
//        System.out.println(xmlMapper.readValue(xml,ResponseDTO.class));


//        try(FileReader file = new FileReader(new File("file.txt"))){
//            System.out.println(file.read());
//        }

    }
}
