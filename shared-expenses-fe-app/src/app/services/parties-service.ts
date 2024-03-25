import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Party } from "../interfaces/party";
import { UUID } from "node:crypto";

@Injectable({
    providedIn: 'root'
})
export class PartiesService {
    constructor(private http: HttpClient) { }

    getData(id: UUID): Observable<Party> {
        return this.http.get<Party>('parties/' + id);
    }
}