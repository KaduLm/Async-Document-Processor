🚀 Async PDF Processor with AI

Sistema distribuído para processamento assíncrono de documentos PDF, com extração de dados via OCR, busca semântica e integração com IA.

🧠 Visão Geral

Este projeto implementa um pipeline completo de processamento de documentos, onde arquivos PDF são recebidos, armazenados, processados de forma assíncrona e enriquecidos com inteligência artificial.

O sistema foi projetado com foco em:

Escalabilidade 📈
Resiliência 🛡️
Processamento assíncrono ⚙️
Inteligência semântica 🤖
🏗️ Arquitetura

O sistema segue uma arquitetura baseada em eventos e separação de responsabilidades:

API → Kafka → Worker → OCR → Parsing → Embeddings → Banco Vetorial
Componentes principais:
API Service: recebe uploads de documentos
Message Broker: Apache Kafka
Worker: processamento assíncrono dos PDFs
Storage: MinIO
Banco de Dados: PostgreSQL + pgvector
⚙️ Tecnologias Utilizadas
Backend
☕ Java 17+
🌱 Spring Boot
Mensageria
📨 Apache Kafka
Processamento de PDF
📄 Apache PDFBox
🔍 Tess4J
Armazenamento
📦 MinIO
IA e Busca Semântica
🤖 OpenAI
🔗 LangChain4j
🧠 pgvector (PostgreSQL)
Infra
🐳 Docker
🔄 Fluxo de Funcionamento
Upload de PDF via API
Armazenamento no MinIO
Publicação de evento no Kafka
Worker consome e processa o documento
Extração de texto:
PDFBox (PDF digital)
OCR (Tesseract via Tess4J)
Parsing de dados estruturados (CPF, nome, etc.)
Geração de embeddings
Armazenamento no banco vetorial
Disponibilização para busca e consulta
🔍 Funcionalidades
✅ Upload assíncrono de documentos
✅ Extração de texto com fallback inteligente (PDF → OCR)
✅ Parsing de dados estruturados
✅ Busca semântica com embeddings
✅ API de perguntas (RAG)
✅ Tratamento de falhas com DLQ (Kafka)
💣 Resiliência

O sistema implementa:

Retry automático no processamento
Dead Letter Queue (DLQ) com Apache Kafka
Logging de falhas
Possibilidade de reprocessamento


📡 Endpoints (exemplo)
Upload de documento
POST /documents
Buscar documentos (semântico)
GET /documents/search?q=consulta cliente
