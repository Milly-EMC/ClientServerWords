# ClientServerWords

Am încercat să realizez o implementare multithreading, compusă dintr-o componentă server și o componentă client.
Componenta client va trimite printr-un socket TCP un cuvânt, iar componenta server va răspunde cu "Word is already in the list" dacă a fost deja trimis respectivul cuvânt, sau "Added to the list" dacă acest cuvant este nou.
Componenta server va realiza stocarea în memorie a cuvintelor adăugate.
