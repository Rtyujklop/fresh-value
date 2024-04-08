import { Injectable } from '@angular/core';
import { Need } from './need'
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class NeedService {

  constructor(private http: HttpClient) 
  { 
    
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private needsUrl = 'http://localhost:8080/Needs';


  getNeeds(): Observable<Need[]>  
  {
    return this.http.get<Need[]>(this.needsUrl)
    .pipe(catchError(this.handleError<Need[]>('getNeeds', []))
    );
  }


  updateNeed(need: Need): Observable<any> {
    return this.http.put(this.needsUrl + '/', need, this.httpOptions).pipe(
      catchError(this.handleError<any>('updateNeed'))
    );
  }

  
  addNeed(need: Need): Observable<Need> {
    return this.http.post<Need>(this.needsUrl, need, this.httpOptions).pipe(
      catchError(this.handleError<Need>('addNeed'))
    );
  }

  deleteNeed(id: number): Observable<Need> 
  {
    return this.http.delete<Need>(this.needsUrl + '/' + id, this.httpOptions).pipe(
      catchError(this.handleError<Need>('deleteNeed'))
    )
  } 

 
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);
      
      return of(result as T);
    };
}
  
}