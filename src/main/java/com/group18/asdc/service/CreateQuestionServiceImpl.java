package com.group18.asdc.service;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.CreateQuestionDao;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.User;

public class CreateQuestionServiceImpl implements CreateQuestionService {

	@Override
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData) {

		CreateQuestionDao theCreateQuestionDao = SystemConfig.getSingletonInstance().getTheCreateQuestionDao();
		UserService theUserService = SystemConfig.getSingletonInstance().getTheUserService();
		User theUser = theUserService.getCurrentUser();
		boolean isQuestiontitleExists = theCreateQuestionDao.isQuestionTitleExists(theBasicQuestionData);
		if (!isQuestiontitleExists) {
			boolean isQuestionTitleCreated = theCreateQuestionDao.createQuestionTitle(theBasicQuestionData);
			if (isQuestionTitleCreated) {
				isQuestiontitleExists = true;
			}
		}
		int isQuestionExistStatus = theCreateQuestionDao.getQuestionId(theBasicQuestionData);

		if (isQuestiontitleExists) {
			if (isQuestionExistStatus == 0) {
				return theCreateQuestionDao.createNumericOrTextQuestion(theBasicQuestionData, theUser);
			}
		}

		return false;
	}

	@Override
	public boolean createMultipleQuestion(MultipleChoiceQuestion theMultipleChoiceChoose) {
		CreateQuestionDao theCreateQuestionDao = SystemConfig.getSingletonInstance().getTheCreateQuestionDao();
		UserService theUserService = SystemConfig.getSingletonInstance().getTheUserService();
		User theUser = theUserService.getCurrentUser();
		BasicQuestionData theBasicQuestionData = new BasicQuestionData();
		theBasicQuestionData.setQuestionTitle(theMultipleChoiceChoose.getQuestionTitle());
		theBasicQuestionData.setQuestionText(theMultipleChoiceChoose.getQuestionText());
		theBasicQuestionData.setQuestionType(theMultipleChoiceChoose.getQuestionType());
		boolean isQuestiontitleExists = theCreateQuestionDao.isQuestionTitleExists(theBasicQuestionData);

		if (!isQuestiontitleExists) {
			boolean isQuestionTitleCreated = theCreateQuestionDao.createQuestionTitle(theBasicQuestionData);
			if (isQuestionTitleCreated) {
				isQuestiontitleExists = true;
			}
		}

		int isQuestionExistStatus = theCreateQuestionDao.getQuestionId(theBasicQuestionData);

		
		if (isQuestiontitleExists) {

			if (isQuestionExistStatus == 0) {
				return theCreateQuestionDao.createMultipleChoiceQuestion(theMultipleChoiceChoose, theUser);
			}
		}

		return false;
	}

}