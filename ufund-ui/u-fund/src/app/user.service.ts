import { Injectable } from "@angular/core";
import { User } from './user';
import { HttpClient,HttpHeaders } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { catchError, tap } from "rxjs/operators";
import { MessageService } from "./message.service";

@Injectable({
    providedIn: 'root'
})
export class UserService{
    private usersUrl = 'https://localhost:4200';

    constructor(
        private http: HttpClient,
        private messageService: MessageService) { }

    setName(name:string | null) {
        if (name == null) {
            localStorage.setItem("user", "");
        }
        else {
            localStorage.setItem("user", String(name));
        }
    }

    getName() {
        return localStorage.getItem("user");
    }

    getUsers(): Observable<User[]> {
        var url = this.usersUrl;
        return this.http.get<User[]>(url).pipe(
            tap(_ => this.log('fetched users')),
            catchError(this.handleError<User[]>('getUsers', []))
        )

    }
    
    getUser(name: string): Observable<User> {
        const url = `${this.usersUrl}/${name}`;
        return this.http.get<User>(url).pipe(
            tap(_ => this.log(`fetched user name =${name}`)),
            catchError(this.handleError<User>(`getUser = ${name}`))
            );
    }

    addUser(user: User): Observable<User> {
        return this.http.post<User>(this.usersUrl, user, this.httpOptions).pipe(
            tap((newUser: User) => this.log(`added user with id=${newUser.name}`)),
            catchError(this.handleError<User>('addUser'))
        )
    }

    deleteUser(name: string): Observable<User>{
        const url = `${this.usersUrl}/${name}`;
        return this.http.get<User>(url).pipe(
            tap(_ => this.log(`deleted user name =${name}`)),
            catchError(this.handleError<User>(`deleteUser}`))
            );
    }

    // updateCart(user: User): Observable<User> {
    //     return(this.http.put<User>(this.usersUrl, user, this.httpOptions).pipe(
    //         tap(_ => this.log(`updated user cart =${user.cart}`)),
    //         catchError(this.handleError<User>(`updateCart = ${user.cart}`))
    //     ));
    // }

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    private log(message: string) {
        this.messageService.add(`UserService: ${message}`);
    }

    private handleError<T>(operation = 'operation', result?: T){
        return (error:any): Observable<T> => {
            console.error(error);

            this.log(`${operation} failed: ${error.message}`);

            return of(result as T);
        };
    }
}