# job4j-pooh

[![Build Status](https://app.travis-ci.com/Spirka/job4j_pooh.svg?branch=master)](https://app.travis-ci.com/Spirka/job4j_pooh)
[![Coverage Status](https://codecov.io/gh/Spirka/job4j_pooh/branch/master/graph/badge.svg)](https://codecov.io/gh/Spirka/job4j_pooh)


В этом проекте мы сделаем аналог асинхронной очереди.

Приложение запускает Socket и ждет клиентов.

Клиенты могут быть двух типов: отправители (publisher), получатели (subscriber).

В качестве клиента будем использовать cURL. https://curl.se/download.html

В качестве протокола будем использовать HTTP. 
