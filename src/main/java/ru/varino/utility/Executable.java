package ru.varino.utility;

import ru.varino.utility.communication.RequestEntity;
import ru.varino.utility.communication.ResponseEntity;

public interface Executable {
    ResponseEntity execute(RequestEntity request);
}
