package com.imshakthi.javatestserver.graphql.fetcher;

import com.imshakthi.javatestserver.graphql.model.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class ShowsDataFetcher {

    private final List<Show> shows = List.of(
            new Show("Stranger Things", 2016),
            new Show("Ozark", 2017),
            new Show("The Crown", 2016),
            new Show("Dead to Me", 2019),
            new Show("Orange is the New Black", 2013)
    );

    @DgsQuery
    public List<Show> shows(@InputArgument String titleFilter) {
        if (titleFilter == null) {
            return shows;
        }

        return shows.stream()
                .filter(show -> show.title().contains(titleFilter))
                .toList();
    }
}
