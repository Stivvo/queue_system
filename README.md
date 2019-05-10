QUEUE SYSTEM

Eliminacode versione 2

Strutturare un programma e scrivere il codice che abbia le seguenti caratteristiche.

Da tastiera il nuovo cliente che arriva dovrà selezionare il tipo di prodotto che richiede:

A: attività finanziare

B: attività comunicazioni (lettere/raccomandate...)

C: attività relative ai pacchi

Sono presenti tre sportelli (1,2,3) associati ai tre servizi sopra indicati

1: sportello riservato ad attività finanziare

2: sportello riservato ad attività comunicazioni (lettere/raccomandate...)

3: sportello riservato ad attività relative ai pacchi

4 e 5: sportello polifunzionale che gestisce di norma le attività di comunicazione, tuttavia in caso di tempi di attesa maggiori di 20 minuti su una delle code o  se lo sportello polifunzionale è privo di richieste potrà prendere in carico la prenotazione appartenente ad altri servizi con tempo di attesa maggiore.  

Da tastiera ogni sportello, al termine del servizio dedicato ad un cliente, dovrà selezionare il proprio numero (1 oppure 2 oppure 3) per accettare il cliente successivo. 

Dovranno essere presenti i seguenti metodi:

metodo NEWENTRY: tale metodo deve gestire l'arrivo di un nuovo cliente al quale deve essere associato un numero composto dalla lettera relativa al servizio richiesto e l'orario di arrivo (es B3 10.50)

metodo NEXT: tale metodo deve gestire la comunicazione del nuovo numero accettato dallo sportello.  
