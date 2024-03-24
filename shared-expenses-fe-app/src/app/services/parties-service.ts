import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Party } from "../interfaces/party";

@Injectable({
    providedIn: 'root'
})
export class PartiesService {
    constructor(private http: HttpClient) { }

    getData(): Observable<Party> {
        // return this.http.get<any>(this.apiUrl);
        return this.http.get<Party>('parties/dc4c885a-0c0c-4575-90a3-890bf78a1090');
    }
}