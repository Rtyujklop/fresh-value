import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { NeedsComponent } from './needs/needs.component';
import { HttpClientModule } from '@angular/common/http';
import { MessageComponent } from './message/message.component';



@NgModule({
    declarations: [
      AppComponent,
      NeedsComponent
    ],
    imports: [
      BrowserModule,
      FormsModule,
      HttpClientModule,
      MessageComponent
    ],
    providers: [],
    bootstrap: [AppComponent]
  })
  export class AppModule { }