## registration

#### Request:

URL: ../user

Method: POST

Body:


| Field    | Type   | Mandatory |
| ---------- | -------- | ----------- |
| login    | String | 1..1      |
| email    | String | 1..1      |
| password | String | 1..1      |

---

#### Response:

Status:

201 - created

Body (at status 201):


| Field  | Type |
| -------- | ------ |
| userId | UUID |

---

400 - bad request

Body (at status 400):


| Field       | Type   |
| ------------- | -------- |
| error       | String |
| description | String |

---

#### Algorithm:

1. Произвести валидацию полей:
   1. При отсутствии хотя бы одного обязательного поля, вернуть код 400:

      * error = 'MISSING_FIELD'
      * message = 'Missing a required field: <отсутствующие поля через запятую>'
   2. Проверить поля на соответствие требованиям:

      * login - латиница, цифры (но не первый символ), длина от 6 до 30 символов
      * email - по регулярке '^([A-Za-z0-9_-]+\.)*[A-Za-z0-9_-]+@[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*\.[A-Za-z]{2,6}$',
        длина не более 60 символов
      * password - любые символы, длина от 6 до 30 символов

      1. Если хотя бы одно поле не прошло проверку, вернуть код 400:
         * error = 'INVALID_FIELD'
         * message = 'Field does not match the requirement: <несоответствующие поля через запятую>'
   3. Проверить в таблице USER наличие записей по полям login и email. Если записи найдены, то вернуть код 400:

      * error = 'LOGIN_USED' или 'EMAIL_USED'
      * message = '<Login или Email> busy'
   4. Создать запись в таблице USER:

      * ID = сгенерированный UUID
      * LOGIN = login из запроса
      * EMAIL = email из запроса
      * PASSWORD = закодированный password из запроса
      * STATUS = 'NEED_CONFIRM'
      * ROLE = 'BASIC'
      * DATA_MODIF = сегодняшняя дата в формате dd-mm-yyyy
   5. Создать запись в таблице ACTION_CODE:

      * CODE = сгенерированный 6-ти значный буквенно-числовой код
      * ACTION = 'REGISTRATION'
      * VALID_TO = сегодняшнаяя дата + 7 дней в формате dd-mm-yyyy
   6. Вернуть код 201:

      * userId = id пользователя из п.4
