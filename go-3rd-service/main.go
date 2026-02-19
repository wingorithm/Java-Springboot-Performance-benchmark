package main

import (
	"encoding/json"
	"log"
	"net/http"
	"time"
)

type DummyResponse struct {
	Status  string `json:"status"`
	Message string `json:"message"`
}

func mockEndpoint(w http.ResponseWriter, r *http.Request) {
	delayStr := r.URL.Query().Get("delay")
	if delayStr != "" {
		delay, err := time.ParseDuration(delayStr)
		if err == nil {
			time.Sleep(delay)
		} else {
			log.Printf("Invalid delay format: %v", err)
		}
	}

	response := DummyResponse{
		Status:  "success",
		Message: "Hello from the 3rd party mock service",
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	json.NewEncoder(w).Encode(response)
}

func main() {
	http.HandleFunc("/api/data", mockEndpoint)

	port := ":9090"
	log.Printf("Starting lightweight mock service on port %s...", port)

	if err := http.ListenAndServe(port, nil); err != nil {
		log.Fatalf("Failed to start server: %v", err)
	}
}
