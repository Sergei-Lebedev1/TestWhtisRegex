package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Класс Product для хранения информации о товаре
class Product {
    private String name;  // Название товара
    private String price;    // Цена товара
    private String article; // Артикул товара

    // Конструктор класса Product
    public Product(String name, String price, String article) {
        this.name = name;
        this.price = price;
        this.article = article;
    }

    // Геттер для артикул
    public String getArticle() {
        return article;
    }

    @Override
    public String toString() {
        return "Товар: " + name + ", Цена: " + price + ", Артикул: " + article;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(); // Список товаров

        // Считываем данные из файла
        try (Scanner scanner = new Scanner(new File("products.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // Читаем строку из файла
                String[] parts = line.split(" "); // Разделяем строку на части

                if (parts.length == 3) { // Проверяем, что строка содержит 3 части
                    String name = parts[0]; // Название товара
                    String price = parts[1]; // Цена товара
                    String article = parts[2]; // Артикул товара

                    products.add(new Product(name, price, article)); // Добавляем товар в список
                }
            }
        } catch (Exception e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }

        // Фильтрация товаров по заданным условиям
        List<Product> filteredProductsA = products.stream()
                .filter(p -> p.getArticle().matches("^[a-zA-Z]+\\d+$")) // а) Латинские буквы затем цифры
                .collect(Collectors.toList());

        List<Product> filteredProductsB = products.stream()
                .filter(p -> p.getArticle().matches("^a.*")) // б) Начинается на символ "а"
                .collect(Collectors.toList());

        List<Product> filteredProductsC = products.stream()
                .filter(p -> p.getArticle().matches(".*\\d.*")) // в) Содержит хотя бы одну цифру
                .collect(Collectors.toList());

        List<Product> filteredProductsD = products.stream()
                .filter(p -> p.getArticle().matches(".*g$")) // г) Заканчивается на символ "g"
                .collect(Collectors.toList());

        // Вывод результатов фильтрации
        System.out.println("Товары, артикулы которых состоят сначала из латинских букв, потом из цифр:");
        filteredProductsA.forEach(System.out::println);

        System.out.println("\nТовары, артикулы которых начинаются на символ 'а':");
        filteredProductsB.forEach(System.out::println);

        System.out.println("\nТовары, артикулы которых содержат хотя бы одну цифру:");
        filteredProductsC.forEach(System.out::println);

        System.out.println("\nТовары, артикулы которых заканчиваются на символ 'g':");
        filteredProductsD.forEach(System.out::println);
    }
}