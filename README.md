# 🚀 Async PDF Processor with AI

Sistema distribuído para processamento assíncrono de documentos PDF, com extração de dados via OCR, busca semântica e integração com IA.

---

## 🧠 Visão Geral

Este projeto implementa um pipeline completo de processamento de documentos, onde arquivos PDF são recebidos, armazenados, processados de forma assíncrona e enriquecidos com inteligência artificial.

O sistema foi projetado com foco em:

- Escalabilidade 📈  
- Resiliência 🛡️  
- Processamento assíncrono ⚙️  
- Inteligência semântica 🤖  

---

## 🏗️ Arquitetura

O sistema segue uma arquitetura baseada em eventos e separação de responsabilidades:


### Componentes principais:

- **API Service**: recebe uploads de documentos  
- **Message Broker**: Apache Kafka  
- **Worker**: processamento assíncrono dos PDFs  
- **Storage**: S3  
- **Banco de Dados**: PostgreSQL + pgvector  

---

## ⚙️ Tecnologias Utilizadas

### Backend
- ☕ Java 25+
- 🌱 Spring Boot

### Mensageria
- 📨 Apache Kafka

### Processamento de PDF
- 📄 Apache PDFBox
- 🔍 Tess4J

### Armazenamento
- 📦 S3

### IA e Busca Semântica
- 🔗 LangChain4j
- 🧠 pgvector (PostgreSQL)

### Infra
- 🐳 Docker
-  Jenkins


---

## 🔄 Fluxo de Funcionamento

1. Upload de PDF via API  
2. Armazenamento no MinIO  
3. Publicação de evento no Kafka  
4. Worker consome e processa o documento  
5. Extração de texto:
   - PDFBox (PDF digital)
   - OCR (Tesseract via Tess4J)  
6. Parsing de dados estruturados (CPF, nome, etc.)  
7. Geração de embeddings  
8. Armazenamento no banco vetorial  
9. Disponibilização para busca e consulta  

---

## 🔄 Fluxo de Funcionamento

1. Recebe requisição de upload
2. Valida arquivo:
   - Tipo (PDF)
   - Tamanho
   - Segurança (opcional)
3. Salva metadados no banco:
   ```json
   {
     "id": "123",
     "status": "UPLOADED"
   }

---

## 💣 Resiliência

O sistema implementa:

- Retry automático no processamento  
- Dead Letter Queue (DLQ) com Apache Kafka  
- Logging de falhas  
- Possibilidade de reprocessamento  

---

## 📡 Endpoints (exemplo)

### Upload de documento

```http
Upload de documento
POST /documents
Buscar documentos (semântico) 
GET /documents/search?q=consulta cliente
