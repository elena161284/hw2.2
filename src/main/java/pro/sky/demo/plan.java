package pro.sky.demo;

public class plan {
    //## Задание 1
    //
    //**Шаг 1**
    //Создать класс для тестирования в пакете test. Создать по одному тесту на каждый эндпоинт контроллера
    // StudentController, используя TestRestTemplate.
    //Критерии оценки: В пакете test создан класс для тестирования StudentController. Для тестирования использовался
    // TestRestTemplate. Для каждого эндпоинта контроллера StudentController создан как минимум один тест.
    //**Шаг 2**
    //Создать еще один класс для тестирования в пакете test. Создать по одному тесту на каждый эндпоинт контроллера
    // FacultyController, используя TestRestTemplate.
    //Критерии оценки: В пакете test создан класс для тестирования FacultyController. Для тестирования использовался
    // TestRestTemplate. Для каждого эндпоинта контроллера FacultyController создан как минимум один тест.

    //## Задание 2
    //
    //**Шаг 1**
    //Создать класс для тестирования в пакете test. Создать по одному тесту на каждый эндпоинт контроллера
    // StudentController, используя WebMvcTest.
    //Критерии оценки: В пакете test создан класс для тестирования StudentController. Для тестирования использовался
    // WebMvcTest. Для каждого эндпоинта контроллера StudentController создан как минимум один тест.
    //**Шаг 2**
    //Создать еще один класс для тестирования в пакете test. Создать по одному тесту на каждый эндпоинт контроллера
    // FacultyController, используя WebMvcTest.
    //Критерии оценки: В пакете test создан класс для тестирования FacultyController. Для тестирования использовался
    // WebMvcTest. Для каждого эндпоинта контроллера FacultyController создан как минимум один тест.
}
//1) Индекс для поиска по имени студента.
//2) Индекс для поиска по названию и цвету факультета.

  //29VRVXKXEQ-eyJsaWNlbnNlSWQiOiIyOVZSVlhLWEVRIiwibGljZW5zZWVOYW1lIjoiZ3VyZ2xlcyB0dW1ibGVzIiwiYXNzaWduZWVOYW1lIjoiIiwiYXNzaWduZWVFbWFpbCI6IiIsImxpY2Vuc2VSZXN0cmljdGlvbiI6IiIsImNoZWNrQ29uY3VycmVudFVzZSI6ZmFsc2UsInByb2R1Y3RzIjpbeyJjb2RlIjoiSUkiLCJmYWxsYmFja0RhdGUiOiIyMDI2LTA5LTE0IiwicGFpZFVwVG8iOiIyMDI2LTA5LTE0IiwiZXh0ZW5kZWQiOmZhbHNlfSx7ImNvZGUiOiJQQ1dNUCIsImZhbGxiYWNrRGF0ZSI6IjIwMjYtMDktMTQiLCJwYWlkVXBUbyI6IjIwMjYtMDktMTQiLCJleHRlbmRlZCI6dHJ1ZX0seyJjb2RlIjoiUFNJIiwiZmFsbGJhY2tEYXRlIjoiMjAyNi0wOS0xNCIsInBhaWRVcFRvIjoiMjAyNi0wOS0xNCIsImV4dGVuZGVkIjp0cnVlfSx7ImNvZGUiOiJQREIiLCJmYWxsYmFja0RhdGUiOiIyMDI2LTA5LTE0IiwicGFpZFVwVG8iOiIyMDI2LTA5LTE0IiwiZXh0ZW5kZWQiOnRydWV9XSwibWV0YWRhdGEiOiIwMTIwMjMwOTE0UFNBWDAwMDAwNSIsImhhc2giOiJUUklBTDoxNjQ5MDU4NzE5IiwiZ3JhY2VQZXJpb2REYXlzIjo3LCJhdXRvUHJvbG9uZ2F0ZWQiOmZhbHNlLCJpc0F1dG9Qcm9sb25nYXRlZCI6ZmFsc2V9-YKRuMTrLQcfyWisYF1q6RhCN+Ub13VOCayGGc6tklGA97oxRM1HCIR0oI5yfTjL7UQYDbNMokT0U0ZQ2obYaUx+MMf7+3FfUYp5dYzP7G9YrEehrGWQ4O8ENrDLDAClB8o8jud9cafW9WTx9hDNd9j2FfjwSaRibClwGBRdO5fSkWlKGhx4tV0K9IyotNYDQzT1QCDRWSxHYGqfDAQI2k+ZAqzNEHValupSM3TKw813kFGKIQndMfw57B6uMzgN6PvuuLpBlghdO3imrgKYj0Q59JYbuXRUpHhPnNLY1XmewdlfcJkvTiRwueCPMNEW/CQEh8X/Als92WCr2H3uFRA==-MIIETDCCAjSgAwIBAgIBDTANBgkqhkiG9w0BAQsFADAYMRYwFAYDVQQDDA1KZXRQcm9maWxlIENBMB4XDTIwMTAxOTA5MDU1M1oXDTIyMTAyMTA5MDU1M1owHzEdMBsGA1UEAwwUcHJvZDJ5LWZyb20tMjAyMDEwMTkwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCUlaUFc1wf+CfY9wzFWEL2euKQ5nswqb57V8QZG7d7RoR6rwYUIXseTOAFq210oMEe++LCjzKDuqwDfsyhgDNTgZBPAaC4vUU2oy+XR+Fq8nBixWIsH668HeOnRK6RRhsr0rJzRB95aZ3EAPzBuQ2qPaNGm17pAX0Rd6MPRgjp75IWwI9eA6aMEdPQEVN7uyOtM5zSsjoj79Lbu1fjShOnQZuJcsV8tqnayeFkNzv2LTOlofU/Tbx502Ro073gGjoeRzNvrynAP03pL486P3KCAyiNPhDs2z8/COMrxRlZW5mfzo0xsK0dQGNH3UoG/9RVwHG4eS8LFpMTR9oetHZBAgMBAAGjgZkwgZYwCQYDVR0TBAIwADAdBgNVHQ4EFgQUJNoRIpb1hUHAk0foMSNM9MCEAv8wSAYDVR0jBEEwP4AUo562SGdCEjZBvW3gubSgUouX8bOhHKQaMBgxFjAUBgNVBAMMDUpldFByb2ZpbGUgQ0GCCQDSbLGDsoN54TATBgNVHSUEDDAKBggrBgEFBQcDATALBgNVHQ8EBAMCBaAwDQYJKoZIhvcNAQELBQADggIBABKaDfYJk51mtYwUFK8xqhiZaYPd30TlmCmSAaGJ0eBpvkVeqA2jGYhAQRqFiAlFC63JKvWvRZO1iRuWCEfUMkdqQ9VQPXziE/BlsOIgrL6RlJfuFcEZ8TK3syIfIGQZNCxYhLLUuet2HE6LJYPQ5c0jH4kDooRpcVZ4rBxNwddpctUO2te9UU5/FjhioZQsPvd92qOTsV+8Cyl2fvNhNKD1Uu9ff5AkVIQn4JU23ozdB/R5oUlebwaTE6WZNBs+TA/qPj+5/we9NH71WRB0hqUoLI2AKKyiPw++FtN4Su1vsdDlrAzDj9ILjpjJKA1ImuVcG329/WTYIKysZ1CWK3zATg9BeCUPAV1pQy8ToXOq+RSYen6winZ2OO93eyHv2Iw5kbn1dqfBw1BuTE29V2FJKicJSu8iEOpfoafwJISXmz1wnnWL3V/0NxTulfWsXugOoLfv0ZIBP1xH9kmf22jjQ2JiHhQZP7ZDsreRrOeIQ/c4yR8IQvMLfC0WKQqrHu5ZzXTH4NO3CwGWSlTY74kE91zXB5mwWAx1jig+UXYc2w4RkVhy0//lOmVya/PEepuuTTI4+UJwC7qbVlh5zfhj8oTNUXgN0AOc+Q0/WFPl1aw5VV/VrO8FCoB15lFVlpKaQ1Yh+DVU8ke+rt9Th0BCHXe0uZOEmH0nOnH/0onD