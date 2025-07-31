# BitCompressor in Java

A simple Java implementation of the Huffman Encoding Algorithm for text compression and decompression.

## 📌 Features

- Builds a Huffman Tree based on character frequencies
- Encodes a given string into a compressed binary format
- Decodes the binary back into the original message
- Uses `HashMap`, `PriorityQueue` and `recursion`

## 🔧 How It Works

1. Frequency map is built from the input string.
2. A priority queue constructs the Huffman Tree.
3. Encoding and decoding maps are generated.
4. Input text is converted to a binary string using the encoder.
5. The binary string is decoded using the decoder.

## 🧪 Example

Encoded: 00011110
Decoded: Code

### Run the program

```bash
javac BitCompressor.java
java  BitCompressor
