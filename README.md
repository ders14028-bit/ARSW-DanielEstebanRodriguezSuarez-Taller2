# Introduction to Naming Schemes, Networks, Clients, and Services with Java

**Author:** Daniel Esteban Rodriguez Suarez  
**Course:** ARSW 2026-i — Escuela Colombiana de Ingeniería Julio Garavito  
**Date:** June 4, 2026

---

## General Description

This workshop covers the fundamentals of network programming in Java using the `java.net` package. Through seven hands-on exercises, the core concepts of TCP and UDP communication are explored, including working with URLs, sockets, datagrams, and Remote Method Invocation (RMI). Each exercise builds on the previous ones, progressing from basic URL handling to distributed object communication via RMI.

**Topics covered:**
- URL creation and parsing
- Reading content from the internet using streams
- TCP client-server communication with sockets
- UDP communication with datagrams
- HTTP server implementation
- Remote Method Invocation (RMI)

---

## Project Structure

```
src/main/java/
├── ejercicio1/   → URL methods
├── ejercicio2/   → URL browser
├── ejercicio3/   → Socket square server
├── ejercicio4/   → Socket trig server
├── ejercicio5/   → HTTP server
├── ejercicio6/   → UDP datagrams clock
└── ejercicio7/   → RMI chat
```

---

## Exercise 1 — URL Methods

### Description
A program that creates a URL object and prints the values returned by its 8 methods: `getProtocol`, `getAuthority`, `getHost`, `getPort`, `getPath`, `getQuery`, `getFile`, and `getRef`.

### Explanation




### Observations



### Test Screenshot

![alt text](<Captura de pantalla 2026-06-04 110616.png>)


---

## Exercise 2 — URL Browser

### Description
A console application that asks the user for a URL, reads its content using streams, and saves the result to a file named `resultado.html`.

### Explanation



### Observations



### Test Screenshot

![alt text](image-2.png)

![alt text](image-1.png)



---

## Exercise 3 — Socket Square Server

### Description
A TCP server that receives a number from a client and responds with the square of that number.

### Explanation



### Observations



### Test Screenshot

![alt text](image-3.png)

![alt text](image-4.png)

---

## Exercise 4 — Socket Trigonometry Server

### Description
A TCP server that receives a number and computes a trigonometric function (sin, cos, or tan) on it. The active function can be changed by sending a message starting with `fun:` (e.g., `fun:sin`). The default function is cosine.

### Explanation



### Observations



### Test Screenshot

![alt text](image-6.png)

![alt text](image-5.png)



---

## Exercise 5 — Multi-Request HTTP Server

### Description
An HTTP server that handles multiple sequential (non-concurrent) requests, returning HTML files and images to the browser.

### Explanation



### Observations



### Test Screenshot

![alt text](image-7.png)



---

## Exercise 6 — UDP Datagram Clock Client

### Description
A UDP client that queries a time server every 5 seconds and displays the current server time. If a response is not received, the last known time is kept. The client continues working even if the server temporarily goes offline.

### Explanation



### Observations



### Test Screenshot



---

## Exercise 7 — RMI Chat Application

### Description
A chat application built with Java RMI. Each instance asks for an IP and port to connect to another instance, and also publishes its own remote object on a specified port so others can connect to it.

### Explanation



### Observations



### Test Screenshot



---

## How to Run



## References

