package com.vick.xiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vick.xiu.entity.Course;
import com.vick.xiu.entity.ExamCourse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyz
 * @since 2019-11-26
 */
public interface ExamCourseMapper extends BaseMapper<ExamCourse> {

    @Select("select id, code, name from exam_course ec join course c on ec.course_id = c.id and ec.exam_id = #{examId} order by ec.sequence_number asc")
    List<Course> courseListByExam(@Param("examId") Long examId);
}
