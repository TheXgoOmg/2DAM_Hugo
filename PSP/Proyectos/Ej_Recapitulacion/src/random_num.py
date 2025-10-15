#!/usr/bin/env python3
import sys
import random
import string
if len(sys.argv) != 3:
    print("Generador de archivos con letras aleatorias");
    print(f"Uso: {sys.argv[0]} <archivo> <bytes>")
    sys.exit(1)
nombre_archivo = sys.argv[1]
num_bytes = int(sys.argv[2])
caracteres = string.ascii_lowercase
with open(nombre_archivo, 'w') as archivo:
    for i in range(num_bytes):
        archivo.write(random.choice(caracteres))
print(f"Archivo '{nombre_archivo}' generado: {num_bytes} bytes")