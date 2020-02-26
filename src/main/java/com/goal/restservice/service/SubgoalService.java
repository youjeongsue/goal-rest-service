package com.goal.restservice.service;

import com.goal.restservice.dto.SubgoalDto;
import java.util.List;

public interface SubgoalService {

    SubgoalDto createSubgoal(SubgoalDto goalDto);

    // TODO: List<SubgoalDto> getSubgoalsByUserId(Long id);

    List<SubgoalDto> getSubgoalsByGoalId(Long id);

    SubgoalDto getSubgoalById(Long id);

    SubgoalDto updateSubgoal(Long id, SubgoalDto goalDto);

    boolean deleteSubgoal(Long id);

}
