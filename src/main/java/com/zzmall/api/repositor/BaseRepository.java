package com.zzmall.api.repositor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @Author Connor
 * @Date 2019/06/03 18:30
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    List<T> findAllByUserId(Integer userId);

    Page<T> findAllByUserId(Pageable pageable, Integer userId);

}
