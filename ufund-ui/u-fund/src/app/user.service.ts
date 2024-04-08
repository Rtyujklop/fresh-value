import { Injectable } from "@angular/core";
import { User } from './user';
import { HttpClient,HttpHeaders } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { catchError, tap } from "rxjs/operators";

@Injectable({
    providedIn: 'root'
})
export class UserService{
    private usersUrl = 'http://localhost:8080/Users/';

    constructor(
        private http: HttpClient) { }

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }


    getName() {
        return localStorage.getItem("user");
    }

    getUsers(): Observable<User[]> {
        const url = this.usersUrl;
        return this.http.get<User[]>(url).pipe(
            catchError(this.handleError<User[]>('getUsers', []))
        )

    }
    
    getUser(name: string): Observable<User[]> {
        const url = `${this.usersUrl}?username=${name}`;
        return this.http.get<User[]>(url).pipe(
            catchError(this.handleError<User[]>(`getUser = ${name}`))
            );
    }

    addUser(user: User): Observable<User> {
        return this.http.post<User>(this.usersUrl, user, this.httpOptions).pipe(
            catchError(this.handleError<User>('addUser'))
        )
    }

    deleteUser(name: string): Observable<User>{
        const url = `${this.usersUrl}/${name}`;
        return this.http.get<User>(url).pipe(
            catchError(this.handleError<User>(`deleteUser}`))
            );
    }

    isUser(): Boolean
    {
        if (localStorage.getItem('token') == 'user-token')
        {
        return true;
        }
        else 
        {
        return false;
        }
    }
   
    private handleError<T>(operation = 'operation', result?: T){
        return (error:any): Observable<T> => {
            console.error(error);
            return of(result as T);
        };
    }
}