package rs.levi9.tech9.team3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.levi9.tech9.team3.domain.Comment;
import rs.levi9.tech9.team3.domain.Rate;
import rs.levi9.tech9.team3.repository.RateRepository;

@Service
public class RateService
{
    private RateRepository rateRepository;

    @Autowired
	public RateService(RateRepository rateRepository) {
		this.rateRepository = rateRepository;
	}
    
	public List<Rate> findAll() {
		return rateRepository.findAll();
	}

	public Rate findOne(Long id) {
		return rateRepository.findOne(id);
	}

	public Rate save(Rate rate) {
		return rateRepository.save(rate);
	}

	public void delete(Long id) {
		rateRepository.delete(id);
	}
    
}