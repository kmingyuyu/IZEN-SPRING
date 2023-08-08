package com.shopmax.gpt;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatResponse {
	private List<Choice> choices;

    public class Choice {

        private int index;
        private Message message;
		

    }
}
