package com.dao;

import com.dto.CustomerCreateRequestDto;
import com.dto.TariffDto;
import com.model.Customer;
import com.model.Tariff;
import com.model.User;
import com.service.TariffService;
import com.service.TariffServiceImpl;
import com.service.UserServiceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestClass {
    public static void main(String[] args) throws IOException {
        TariffService tariffService = new TariffServiceImpl();
        List<Tariff> tariffs = tariffService.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (Tariff tariff : tariffs) {
            stringBuilder.append(tariff);
            stringBuilder.append('\n');
        }
        File file = new File("WEB-INF/sample.txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.append(stringBuilder);
        } finally {
            if (writer != null) writer.close();
        }
        System.out.println(writer);
    }

    }

