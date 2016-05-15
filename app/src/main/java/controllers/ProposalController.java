package controllers;

import java.util.List;

import dao.IProposalDao;
import models.Announcement;
import models.Proposal;
import services.IResult;
import webservices.ProposalParse;

/**
 * Created by kleber on 10/05/16.
 */
public class ProposalController {

    private static IProposalDao proposalDao = new ProposalParse();

    public static void send(final Proposal proposal, final IResult<Proposal> result) {
        proposalDao.send(proposal, new IResult<Proposal>() {
            @Override
            public void onSuccess(List<Proposal> list) {

            }

            @Override
            public void onSuccess(Proposal obj) {
                result.onSuccess(obj);
            }

            @Override
            public void onError(String msg) {
                result.onError(msg);
            }
        });
    }


    public static void getByAnnouncement(Announcement announcement, final IResult<Proposal> result){
        proposalDao.getByAnnouncement(announcement, new IResult<Proposal>() {
            @Override
            public void onSuccess(List<Proposal> list) {
                result.onSuccess(list);
            }

            @Override
            public void onSuccess(Proposal obj) {

            }

            @Override
            public void onError(String msg) {
                result.onError(msg);
            }
        });
    }

}
