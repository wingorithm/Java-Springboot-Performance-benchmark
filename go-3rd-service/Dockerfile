# Build stage
FROM golang:1.22-alpine AS builder
WORKDIR /app
COPY . .
RUN go build -o 3rd-service main.go

# Run stage
FROM alpine:latest
WORKDIR /app
COPY --from=builder /app/3rd-service .

EXPOSE 9090
ENTRYPOINT ["./3rd-service"]