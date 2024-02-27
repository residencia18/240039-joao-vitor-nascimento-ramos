import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SearchComponent } from './Wiki/Componets/search/search.component';
import { ResultComponent } from './Wiki/Componets/result/result.component';
import { PipeBoldPipe } from './Wiki/Pipes/pipe-bold.pipe';
import { WikiService } from './Wiki/Services/service-wiki.service';
import { RouterWikiComponent } from './router-wiki.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
    declarations: [
        RouterWikiComponent,
        SearchComponent,
        ResultComponent,
        PipeBoldPipe,
    ],
    imports: [ CommonModule,
        FormsModule,
        HttpClientModule
     ],
    exports: [],
    providers: [WikiService],
})
export class WikiModule {}