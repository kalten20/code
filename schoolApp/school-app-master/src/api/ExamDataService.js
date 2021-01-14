import axios from 'axios'
import {API_URL,} from './Constants'

class ExamDataService {

    getClassExams(classId){
        return axios.get(`${API_URL}/exams/class/${classId}`)
    }

    getUnavailableDatesByTeacherIdAndClassId(teacherId, classId) {
        return axios.get(`${API_URL}/exams/constraints/${teacherId}/${classId}/unavailable`)
    }

    createExam(exam) {
        return axios.post(`${API_URL}/exams/create`, exam)

    }

    deleteExam(examId) {
        return axios.delete(`${API_URL}/exams/delete/${examId}`)
    }




}

export default new ExamDataService()